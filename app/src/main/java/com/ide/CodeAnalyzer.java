package com.ide;

import com.example.advancedide.SimpleLangLexer;
import com.example.advancedide.SimpleLangParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class CodeAnalyzer {

    public static String analyze(String code) {
        SimpleLangLexer lexer = new SimpleLangLexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleLangParser parser = new SimpleLangParser(tokens);
        ParseTree tree = parser.prog();

        StringBuilder output = new StringBuilder();
        output.append("Parse Tree:\n").append(tree.toStringTree(parser)).append("\n");

        return output.toString();
    }
}
