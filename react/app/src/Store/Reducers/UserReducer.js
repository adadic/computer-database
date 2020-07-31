import * as userActions from '../Action/UserAction'

const initialState = {
    user: {
        username: "",
        email: "",
        role: {
            id: 0,
            roleName: ""
        }
    }
}

const userReducer = (state = initialState, action) => {

    switch (action.type) {
        case userActions.GETUSER:
            
            return {...state, user: action.payload};

        default:
            return state;
    }
    
}

export default userReducer