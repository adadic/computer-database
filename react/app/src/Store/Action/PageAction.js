export const ORDER = "ORDER";
export const ORDERBY = "ORDERBY";
export const SELECTED = "SELECTED";
export const PAGE = "PAGE";
export const ROWSPERPAGE = "ROWSPERPAGE";

export const setOrder = (order) => {

    return {
        type: ORDER,
        payload: order
    };
}

export const setOrderBy = (orderby) => {

    return {
        type: ORDERBY,
        payload: orderby
    };
}

export const setSelected = (selected) => {

    return {
        type: SELECTED,
        payload: selected
    };
}

export const setPage = (page) => {

    return {
        type: PAGE,
        payload: page
    };
}

export const setRowsPerPage = (rows) => {

    return {
        type: ROWSPERPAGE,
        payload: rows
    };
}