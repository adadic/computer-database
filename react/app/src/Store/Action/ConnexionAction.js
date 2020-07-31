export const TOKEN = "TOKEN";
export const ISCONNECTED = "ISCONNECTED";

export const setToken = token => {

    return {
        type: TOKEN,
        payload: token,
    };
}

export const isConnected = isConnected => {
    
    return {
        type: ISCONNECTED,
        payload: isConnected
    }
}
