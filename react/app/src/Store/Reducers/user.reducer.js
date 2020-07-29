import * as userActions from '../Action/user.action'

const initialState = {
    token: "fffdf",
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