export const SEARCH = "SEARCH";

export const newSearch = (search) => {

    return {
        type: SEARCH,
        payload: search
    };
}