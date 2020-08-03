import * as actionTypes from "../Action/SearchAction";

const initialState = {
    search: "",
    searchMode: false
}

const searchReducer = (state = initialState, action) => {

    switch (action.type) {
        case actionTypes.SEARCH:

            return {...state, search: action.payload};
        case actionTypes.SEARCHMODE:

            return {...state, searchMode: action.payload};
        default:

            return state;
    }
}

export default searchReducer;