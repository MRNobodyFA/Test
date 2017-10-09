package com.didi.virtualapk.hooker

import com.android.build.gradle.api.ApkVariant
import com.android.build.gradle.internal.pipeline.TransformTask
import com.android.build.gradle.internal.transforms.ProGuardTransform
import com.android.builder.dependency.JarDependency
import org.gradle.api.InvalidUserDataException
import org.gradle.api.Project
import com.didi.virtualapk.collector.dependence.DependenceInfo

/**
 * Apply host mapping file to maintain compatibility between the plugin and host apk.
 * And input the stripped jar files as library, to prevent proguard errors
 *
 * @author zhengtao
 */
class ProguardHooker extends GradleTaskHooker<TransformTask> {

    public static final String MAPPING_KEY = "applyMapping"

    ProguardHooker(Project project, ApkVariant apkVariant) {
        super(project, apkVariant)
    }

    @Override
    String getTaskName() {
        return "proguard"
    }

    /**
     * Before the run of proguard, specifies the mapping file to reuse when obfuscating,
     * if classes in host apk is obfuscated, we need to ensure compatibility by specifying
     * the mapping file.
     *
     * In addition, we need to input the stripped jars into the Proguard program in the
     * form of libarary, via the libraryJar(protected) method
     *
     * @param task Gradle task of proguard
     */
    @Override
    void beforeTaskExecute(TransformTask task) {

        def proguardTransform = task.transform as ProGuardTransform

        File applyMappingFile;

        //Specifies the proguard mapping file through ${ MAPPING_KEY }
        if (project.hasProperty(MAPPING_KEY)) {
            applyMappingFile = new File(project.properties[MAPPING_KEY])
            if (!applyMappingFile.exists()) {
                throw new InvalidUserDataException("${project.properties[MAPPING_KEY]} does not exist")
            }
            if (!applyMappingFile.isFile()) {
                throw new InvalidUserDataException("${project.properties[MAPPING_KEY]} is not a file")
            }
        }

        //Default to use the mapping file generated by host apk
        if (virtualApk.applyHostMapping && applyMappingFile == null) {
            applyMappingFile = new File(project.rootProject.projectDir, "host/mapping.txt")
        }

        if (applyMappingFile?.exists()) {
            proguardTransform.applyTestedMapping(applyMappingFile)
        }

        virtualApk.stripDependencies.each {
            proguardTransform.libraryJar(it.jarFile)
            if (it.dependenceType == DependenceInfo.DependenceType.AAR) {
                it.localDependencies.each { JarDependency localJar ->
                    proguardTransform.libraryJar(localJar.jarFile)
                }
            }
        }
    }

    @Override
    void afterTaskExecute(TransformTask task) { }
}