import * as actionTypes from "./Action/ActionTypes"

const initialState = {
    search: "",
    token: "",
    isConnected: false,
}

const reducer = (state = initialState, action) => {

    switch (action.type) {
        case actionTypes.SEARCH:

            return {...state, search: action}
    }
    return state
}

export default reducer