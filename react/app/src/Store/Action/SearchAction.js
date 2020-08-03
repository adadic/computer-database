export const SEARCH = "SEARCH";
export const SEARCHMODE = "SEARCHMODE";

export const newSearch = (search) => {

    return {
        type: SEARCH,
        payload: search
    };
}

export const searchMode = (searchMode) => {

    return {
        type: SEARCHMODE,
        payload: searchMode
    };
}