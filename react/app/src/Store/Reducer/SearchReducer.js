import * as actionTypes from "../Action/SearchAction"

const initialState = {
    search: ""
}

const searchReducer = (state = initialState, action) => {

    switch (action.type) {
        case actionTypes.SEARCH:

            return {...state, search: action.payload}
        default:
            return state
    }
}

export default searchReducer