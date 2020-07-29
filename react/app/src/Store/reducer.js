import * as actionTypes from "./Action/ActionTypes"

const initialState = {
    search: "",
    token: "",
    isConnected: false,
}

const reducer = (state = initialState, action) => {

    switch (action.type) {
        case actionTypes.SEARCH:
            const newSearch =  action.search;

            return {...state, search: newSearch}
    }
    return state
}

export default reducer