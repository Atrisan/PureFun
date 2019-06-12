package de.simpleproglang.purefun._symboltable;

import de.monticore.ModelingLanguageFamily;
import de.monticore.io.paths.ModelPath;
import de.monticore.symboltable.GlobalScope;

/**
 * @author Pascal Siewert
 * This class provides methods for symboltable creation.
 */
public class PureFunScopeCreator {

    private static PureFunScopeCreator creator;

    private PureFunScopeCreator() {}

    private static PureFunScopeCreator getInstance() {
        if (creator == null) {
            creator = new PureFunScopeCreator();
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
