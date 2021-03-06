package de.simpleproglang.purefun._symboltable;

import de.monticore.ast.ASTNode;
import de.monticore.modelloader.ModelingLanguageModelLoader;

import java.util.Optional;

public class PureFunLanguage extends PureFunLanguageTOP {

    private static final String FILE_ENDING = "pf";

    public PureFunLanguage() {
        super("PureFun Language", FILE_ENDING);

        setModelNameCalculator(new PureFunModelNameCalculator());
    }

    @Override
    protected ModelingLanguageModelLoader<? extends ASTNode> provideModelLoader() {
        return new PureFunModelLoader(this);
    }
}
