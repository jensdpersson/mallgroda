package com.jenspersson.mallgroda.proc;


enum Directive {
    
    
    
        // Order of enum values decide parent-child ordering of ast. 
        repeat,
        condition, 
        text,
        html,
        replace, 
        content, 
        include;
}