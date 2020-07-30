import { combineReducers } from "redux";
import userReducer from "./UserReducer";
import searchReducer from './SearchReducer';

export default combineReducers({
    
    userReducer,
    searchReducer,
});