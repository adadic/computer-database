import {searchOption} from "../Reducers/RootReducer";

export function getSearch(state){

    return state[searchOption].search;
}