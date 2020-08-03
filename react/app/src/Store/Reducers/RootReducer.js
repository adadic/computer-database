import { combineReducers } from "redux";
import connexionReducer from "./ConnexionReducer";
import searchReducer from './SearchReducer';
import userReducer from "./UserReducer";
import pageReducer from "./PageReducer";

export const searchOption = 'searchOption';
export const tokenOption = 'tokenOption';
export const userOption = 'userOption';
export const pageOption = 'pageOption';


const search = { [searchOption]: searchReducer };
const token = { [tokenOption]: connexionReducer };
const user = { [userOption]: userReducer };
const page = { [pageOption]: pageReducer };

export const RootReducer = combineReducers({
    
    ...token,
    ...search,
    ...user,
    ...page,
});