import * as actionTypes from "./Action/ActionTypes"
import { combineReducers } from "redux";
import userReducer from "./Reducers/user.reducer";

const initialState = {
    search: "",
    token: "fffdf",
    isConnected: false,
}

const reducer = (state = initialState, action) => {

    switch (action.type) {
        case actionTypes.SEARCH:

            return {...state, search: action};
    }
    return state
}

//export default reducer

export default combineReducers({
    userReducer,

})