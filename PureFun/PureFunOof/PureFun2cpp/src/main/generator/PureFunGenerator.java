
import de.monticore.generating.GeneratorSetup;
import de.monticore.generating.GeneratorEngine;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import de.simpleproglang.purefun._ast.*;

public class PureFunGenerator {

    public void generate(ASTModule ast, String filename) {
        GeneratorSetup gs = new GeneratorSetup();
        gs.setCommentStart("/*");
        gs.setCommentEnd("*/");
        gs.setTracing(true);
        gs.setDefaultFileExtension("cxx");

        GeneratorEngine engine = new GeneratorEngine(gs);
        engine.generate("../templates/module.ftl", Paths.get(filename), ast);
    }
}