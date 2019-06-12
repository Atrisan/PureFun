package de.simpleproglang.purefun.symboltable;

import de.monticore.ModelingLanguageFamily;
import de.monticore.io.paths.ModelPath;
import de.monticore.symboltable.GlobalScope;
import de.simpleproglang.purefun._symboltable.PureFunLanguage;

/**
 * @author Pascal Siewert
 * This class provides methods for symboltable creation.
 */
public class PureFunSymbolTableCreator {

    private static PureFunSymbolTableCreator creator;

    private PureFunSymbolTableCreator() {}

    private static PureFunSymbolTableCreator getInstance() {
        if (creator == null) {
            creator = new PureFunSymbolTableCreator();
        }

        return creator;
    }

    /**
     * Creates the symboltable for the specified modelPath
     * @param modelPath
     * @return
     * GlobalScope
     */
    public static GlobalScope createGlobalScope(ModelPath modelPath) {
        return getInstance().doCreateGlobalScope(modelPath);
    }

    protected GlobalScope doCreateGlobalScope(ModelPath modelPath) {
        final PureFunLanguage pfLang = new PureFunLanguage();
        final ModelingLanguageFamily languageFamily = new ModelingLanguageFamily();
        languageFamily.addModelingLanguage(pfLang);
        return new GlobalScope(modelPath, languageFamily);
    }
}
