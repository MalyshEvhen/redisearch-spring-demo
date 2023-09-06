// import type { PageLoad } from './$types';

// export const load = (async ({ fetch }) => {
//     const articlesRes = await fetch('http://localhost:8080/api/articles/get-by-id/01H9MEHDAXJVM8TPKKWZ5KH2AY',
//         {
//             headers: {
//                 'Accept': 'application/json',
//                 'Content-Type': 'application/json'
//             },
//             method: 'GET',
//             mode: 'no-cors',
//         })

//     return {
//         response: await articlesRes.json(),
//     };
// }) satisfies PageLoad;