package com.liukhtenko.xmlxsdparsing.parser;
import com.liukhtenko.xmlxsdparsing.entity.Paper;

import java.util.HashSet;
import java.util.Set;
public abstract class AbstractPeriodicalBuilder {
    // protected так как к нему часто обращаются из подкласса
    protected Set<Paper> papers;
    public AbstractPeriodicalBuilder() {
        papers = new HashSet<Paper>();
    }

    public Set<Paper> getPapers() {
        return papers;
    }
    abstract public void buildSetPapers(String fileName);
}

