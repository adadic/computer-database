import {userOption} from "../Reducers/RootReducer";

export function getUser(state){

    return state[userOption].user;
}