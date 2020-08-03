import * as userActions from '../Action/UserAction'

const initialState = {
    user: {
        userName: "",
        email: "",
        roleName: "",
        password: ""
    }
}

const userReducer = (state = initialState, action) => {

    switch (action.type) {
        case userActions.SETUSER:
            
            return {...state, user: action.payload};

        default:
            return state;
    }
    
}

export default userReducer