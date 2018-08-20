package com.ngti.leandro.lol.model.match;

import com.ngti.leandro.lol.model.match.MatchContainer;

public class MatchResponse {

    public class JSONResponse {
        private MatchContainer[] match;

        public MatchContainer[] getMatch() {
            return match;
        }
    }

}
