package com.ide;

import org.antlr.v4.runtime.tree.ParseTree;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.ext.JGraphXAdapter;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.swing.mxGraphComponent;

import javax.swing.*;
import java.awt.*;

public class ParseTreeViewer {

    public static void show(ParseTree tree) {
        DefaultDirectedGraph<String, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);
        addNodes(g, tree, null);

        JGraphXAdapter<String, DefaultEdge> graphAdapter = new JGraphXAdapter<>(g);
        new mxCompactTreeLayout(graphAdapter).execute(graphAdapter.getDefaultParent());

        JFrame frame = new JFrame();
        frame.getContentPane().add(new mxGraphComponent(graphAdapter));
        frame.setTitle("Parse Tree Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static void addNodes(DefaultDirectedGraph<String, DefaultEdge> g, ParseTree tree, String parent) {
        String label = tree.toStringTree();
        g.addVertex(label);
        if (parent != null) {
            g.addEdge(parent, label);
        }
        for (int i = 0; i < tree.getChildCount(); i++) {
            addNodes(g, tree.getChild(i), label);
        }
    }
}
