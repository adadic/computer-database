import { combineReducers } from "redux";
import connexionReducer from "./ConnexionReducer";
import searchReducer from './SearchReducer';
import userReducer from "./UserReducer";

export const searchOption = 'searchOption';
export const tokenOption = 'tokenOption';
export const userOption = 'userOption';


const search = { [searchOption]: searchReducer };
const token = { [tokenOption]: connexionReducer };
const user = { [userOption]: userReducer };

export const RootReducer = combineReducers({
    
    ...token,
    ...search,
    ...user,
});