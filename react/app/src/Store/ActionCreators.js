import * as actionTypes from "./Action/ActionTypes";

export const newSearch = search => {
    return {
        type: actionTypes.SEARCH,
        search,
    }
}