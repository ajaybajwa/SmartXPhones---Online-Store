export default {
    oidc: {
        clientId: '0oaiopqa9a2XC4j7L5d7',
        issuer: 'https://dev-22186428.okta.com/oauth2/default',
        redirectUri: window.location.origin + '/login/callback',
        postLogoutRedirectUri: 'http://localhost:4201/login',
        scopes: ['openid', 'profile', 'email']
    }
}

