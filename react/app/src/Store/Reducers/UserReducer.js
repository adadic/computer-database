import * as userActions from '../Action/UserAction'

const initialState = {
    token: "",
    isConnected: false,
}

const userReducer = (state = initialState, action) => {

    switch (action.type) {
        case userActions.TOKEN:
            
            return {...state, token: action.payload};
    }
    return state
}

export default userReducer