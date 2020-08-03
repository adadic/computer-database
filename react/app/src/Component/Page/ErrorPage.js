import React from "react";

function ErrorPage(props) {

    return(
        <div>
            {props.errorMessage
            ?
                <div>{props.errorMessage}</div>
            :
                <div>ERROR!!!!!!</div>
            }
        </div>
    );
}
export default ErrorPage;