// 콜백 방식
const wget1 = ( url, callback ) => {
    console.log( 'wget1 [' + url + ']' );
    setTimeout( () => {
        const response = {
            data: 'Hello World1'
        }
        callback(response);
    }, 3000 );
};

const wget2 = ( url ) => {
    console.log( 'wget2 [' + url + ']' );
    return new Promise((resolve, reject) => {
        setTimeout( () => {
            const response = {
                data: 'Hello World2'
            }
            // resolve(response);
            reject( 'fails: wget2' );
        }, 1000 );
    });
};

const _fetch = async ( url ) => {
    try {
        console.log( '_fetch [' + url + ']' );
        let response = await wget2( url );
        return response;
    } catch ( err ) {
        console.log( err );
    }
}

wget1( 'http://www.corock.com/api', response => console.log(response) );
wget2( 'http://www.corock.com/api2' )
    .then( response => console.log(response) )
    .catch( err => console.error(err) );

_fetch( 'http://www.corock.com/api3' )
    .then( response => console.log(response) )

console.log('do something');