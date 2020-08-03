export const SETUSER = 'SETUSER';

export const setUser = user => {

    return {
        type: SETUSER,
        payload: user,
    };
}

