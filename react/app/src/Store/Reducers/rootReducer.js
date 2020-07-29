import { combineReducers } from "redux";
import userReducer from "./userReducer";
import searchReducer from './searchReducer';

export default combineReducers({
    userReducer,
    searchReducer,
})