import * as userActions from '../Action/PageAction';

const initialState = {

    order: 'asc',
    orderBy: '',
    selected: [],
    page: 0,
    rowsPerPage: 10
}

const pageReducer = (state = initialState, action) => {

    switch (action.type) {
        case userActions.ORDER:

            return { ...state, order: action.payload };
        
        case userActions.ORDERBY:

            return { ...state, orderBy: action.payload };
        
        case userActions.SELECTED:

            return { ...state, selected: action.payload };

        case userActions.PAGE:

            return { ...state, page: action.payload };

        case userActions.ROWSPERPAGE:

            return { ...state, rowsPerPage: action.payload };

        default:
            return state;
    }

}

export default pageReducer