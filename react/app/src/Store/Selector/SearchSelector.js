import {searchOption} from "../Reducers/RootReducer";

export function getSearch(state){

    return state[searchOption].search;
}

export function getSearchMode(state){

    return state[searchOption].searchMode;
}