import {pageOption} from "../Reducers/RootReducer";

export function getOrder(state){

    return state[pageOption].order;
}

export function getOrderBy(state){

    return state[pageOption].orderBy;
}

export function getSelected(state){

    return state[pageOption].selected;
}

export function getPage(state){

    return state[pageOption].page;
}

export function getRowsPerPage(state){

    return state[pageOption].rowsPerPage;
}