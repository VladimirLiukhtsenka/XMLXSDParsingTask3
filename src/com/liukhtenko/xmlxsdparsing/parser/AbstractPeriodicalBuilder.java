package com.liukhtenko.xmlxsdparsing.parser;

import com.liukhtenko.xmlxsdparsing.entity.Paper;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPeriodicalBuilder {
    protected Set<Paper> papers;

    AbstractPeriodicalBuilder() {
        papers = new HashSet<>();
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    abstract public void buildSetPapers(String fileName);
}

