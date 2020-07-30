import {tokenOption} from "../Reducers/RootReducer";

export function getToken(state){

    return state[tokenOption];
}