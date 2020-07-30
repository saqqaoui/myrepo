export class Error {

    errorMessage: String;
    errorStatusCode: Number;
    errorStatus: String;
    errorMessages: Array<String> = new Array;
    globalMessage: String;
​​
static errorMapper(errorClient: Error, errorServer: any) {
    console.log(errorServer);
    if (errorServer) {
        errorClient.errorStatusCode = errorServer.status;
        if (errorServer.status == 0) {
            errorClient.errorMessage = "No response from server";
        } else if (errorServer.error) {
            errorClient.errorMessage = errorServer.error.error;
            errorClient.errorStatus = errorServer.error.status;
            errorClient.globalMessage = errorServer.error.message;
            if (errorServer.error.errors) {
                for (const [key, value] of errorServer.error.errors.entries()) {
                    errorClient.errorMessages.push(value);
                }
            }
        }
    }
}
}