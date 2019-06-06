package de.simpleproglang.purefun._symboltable;

import de.monticore.symboltable.ArtifactScope;
import de.monticore.symboltable.ResolvingConfiguration;
import de.monticore.symboltable.Scope;
import de.simpleproglang.purefun._ast.ASTModule;
import de.simpleproglang.purefun._ast.ASTVariable;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class PureFunSymbolTableCreator extends PureFunSymbolTableCreatorTOP {

    public PureFunSymbolTableCreator(ResolvingConfiguration resolvingConfig, Scope enclosingScope) {
        super(resolvingConfig, enclosingScope);
    }

    public PureFunSymbolTableCreator(ResolvingConfiguration resolvingConfig, Deque<Scope> scopeStack) {
        super(resolvingConfig, scopeStack);
    }

    /**
     * Starts the creation of the symbol table
     *
     * @param rootNode
     * @return
     */
    @Override
    public Scope createFromAST(ASTModule rootNode) {
        requireNonNull(rootNode);

        final ArtifactScope artifactScope = new ArtifactScope(Optional.empty(), "", new ArrayList<>());
        putOnStack(artifactScope);

        rootNode.accept(this);

        return artifactScope;
    }

    /**
     * Override the creation of a variable Symbol and extend it with
     * additional information for the symbol
     * e.g. which type it has
     *
     * @param ast
     * @return
     */
    @Override
    protected VariableSymbol create_Variable(ASTVariable ast) {
        // TODO: Use our own TypePrinter to get the type
        return super.create_Variable(ast);
    }
}
