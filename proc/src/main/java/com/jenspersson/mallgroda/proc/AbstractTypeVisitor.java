package com.jenspersson.mallgroda.proc;

import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.IntersectionType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.NullType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.TypeVisitor;
import javax.lang.model.type.UnionType;
import javax.lang.model.type.WildcardType;

public abstract class AbstractTypeVisitor<CALLBACK> implements TypeVisitor<Boolean, CALLBACK> {
        
    @Override
    public Boolean visit(TypeMirror t, CALLBACK p) {
        return false;
    }

    @Override
    public Boolean visit(TypeMirror t) {
        return false;
    }

    @Override
    public Boolean visitArray(ArrayType t, CALLBACK p) {
        return false;
    }

    @Override
    public Boolean visitExecutable(ExecutableType t, CALLBACK p) {
        return false;
    }

    @Override
    public Boolean visitDeclared(DeclaredType t, CALLBACK p) {
        return false;
    }
    
    @Override
    public Boolean visitError(ErrorType t, CALLBACK p) {
        return false;
    }
    @Override
    public Boolean visitIntersection(IntersectionType t, CALLBACK p) {
        return false;
    }
            
    @Override
    public Boolean visitNoType(NoType t, CALLBACK p) {
        return false;
    }

    @Override
    public Boolean visitNull(NullType t, CALLBACK p) {
        return false;
    }
            
    @Override
    public Boolean visitPrimitive(PrimitiveType t, CALLBACK p) {
        return false;
    }
            
    @Override
    public Boolean visitTypeVariable(TypeVariable t, CALLBACK p) {
        return false;
    }
            
    @Override
    public Boolean visitUnion(UnionType t, CALLBACK p) {
        return false;
    }
            
    @Override
    public Boolean visitUnknown(TypeMirror t, CALLBACK p) {
        return false;
    }

    @Override
    public Boolean visitWildcard(WildcardType t, CALLBACK p) {
        return false;
    }
}