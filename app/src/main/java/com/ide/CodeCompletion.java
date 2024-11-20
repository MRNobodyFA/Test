package com.ide;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.wellbehaved.event.EventPattern;
import org.fxmisc.wellbehaved.event.Nodes;
import org.fxmisc.wellbehaved.event.template.InputMapTemplate;

import java.util.Arrays;
import java.util.List;

public class CodeCompletion {

    private static final List<String> KEYWORDS = Arrays.asList(
            "abstract", "assert", "boolean", "break", "byte", 
            "case", "catch", "char", "class", "const", 
            "continue", "default", "do", "double", "else", 
            "enum", "extends", "final", "finally", "float", 
            "for", "goto", "if", "implements", "import", 
            "instanceof", "int", "interface", "long", "native", 
            "new", "null", "package", "private", "protected", 
            "public", "return", "short", "static", "strictfp", 
            "super", "switch", "synchronized", "this", "throw", 
            "throws", "transient", "try", "void", "volatile", "while"
    );

    public static void attach(CodeArea codeArea) {
        Nodes.addInputMap(codeArea, InputMapTemplate.consume(
                EventPattern.keyPressed(javafx.scene.input.KeyCode.SPACE, javafx.scene.input.KeyCombination.CONTROL_DOWN),
                e -> {
                    int caretPosition = codeArea.getCaretPosition();
                    int wordStartPosition = caretPosition;
                    while (wordStartPosition > 0 && !Character.isWhitespace(codeArea.getText(wordStartPosition - 1, wordStartPosition))) {
                        wordStartPosition--;
                    }
                    String word = codeArea.getText(wordStartPosition, caretPosition);
                    String suggestion = KEYWORDS.stream()
                            .filter(keyword -> keyword.startsWith(word))
                            .findFirst().orElse("");
                    if (!suggestion.isEmpty()) {
                        codeArea.replaceText(wordStartPosition, caretPosition, suggestion);
                    }
                }
        ));
    }
}
