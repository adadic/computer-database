import React, { useState } from 'react';
import { Button } from '@material-ui/core';

function ShowUser(props) {
    return(
        <div>
            <p>username</p>
            <p>email</p>
            <Button>Logout</Button>
        </div>
    );
}

export default ShowUser;