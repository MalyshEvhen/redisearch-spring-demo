import type { PageLoad } from './$types';

export const load = (async ({ fetch }) => {
    const postsRes = await fetch('https://dummyjson.com/posts')
    const postsData = await postsRes.json()
    const posts = postsData.posts

    return {
        posts: posts
    };
}) satisfies PageLoad;