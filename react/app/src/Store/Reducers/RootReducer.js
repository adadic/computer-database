import { combineReducers } from "redux";
import userReducer from "./UserReducer";
import searchReducer from './SearchReducer';

export const searchOption = 'searchOption';
export const tokenOption = 'tokenOption';

const search = { [searchOption]: searchReducer };
const token = { [tokenOption]: userReducer}

export const RootReducer = combineReducers({
    
    ...token,
    ...search,
});