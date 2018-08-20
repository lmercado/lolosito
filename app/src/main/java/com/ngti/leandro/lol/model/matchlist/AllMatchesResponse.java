package com.ngti.leandro.lol.model.matchlist;

import com.ngti.leandro.lol.model.matchlist.AllMatches;

public class AllMatchesResponse {

    public class JSONResponse {
        private AllMatches[] matches;

        public AllMatches[] getMatches() {
            return matches;
        }
    }

}
