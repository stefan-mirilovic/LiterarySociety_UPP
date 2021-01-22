export class UserWithToken {

    public accessToken: string;
    public expiresIn: number;
    public userId: number;
    public userType: string;
    public passwordChanged: boolean;
    public numLogin: number;
    public expirationDate: Date;
    public email: string;

    constructor(accessToken: string, expiresIn: number, userId: number, userType: string, passwordChanged: boolean, numLogin: number,
        email: string) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.userId = userId;
        this.userType = userType;
        this.passwordChanged = passwordChanged;
        this.numLogin = numLogin;
        this.expirationDate = new Date(new Date().getTime() + expiresIn * 1000);
        this.email = email;
    }

    get getToken() {
        if(!this.expirationDate || new Date() > this.expirationDate) {
            return null;
        }

        return this.accessToken;
    }
}