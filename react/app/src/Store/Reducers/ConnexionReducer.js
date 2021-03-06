import * as userActions from '../Action/ConnexionAction'

const initialState = {
    token: "",
    isConnected: false,
}

const connexionReducer = (state = initialState, action) => {

    switch (action.type) {
        case userActions.TOKEN:
            
            return {...state, token: action.payload};

        case userActions.ISCONNECTED:

            return {...state, isConnected: action.payload};

        default:
            return state;
    }
}

export default connexionReducer