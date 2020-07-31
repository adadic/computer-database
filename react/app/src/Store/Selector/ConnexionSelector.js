import {tokenOption} from "../Reducers/RootReducer";

export function getToken(state){

    return state[tokenOption].token;
}

export function isConnected(state){

    return state[tokenOption].isConnected;
}
