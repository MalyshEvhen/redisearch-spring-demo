<script lang="ts">
    import type { PageData } from "./$types";

    export let data: PageData;
    const { posts } = data;
</script>

<div class="container h-full mx-auto gap-8 flex flex-col">
	<div class="flex content-center justify-between">
		<h2>Articles</h2>
		<a href="/posts" class="btn variant-ghost-primary">
			New Post
		</a>
	</div>
	<div class="grid grid-cols-3 gap-4">
		{#each posts as post}
			<div
				class="card p-4 variant-ghost-warning flex flex-col gap-2 relative"
			>
				<button
					class="btn-icon btn-icon-sm variant-filled-error absolute -top-1.5 -right-1.5"
					>X</button
				>
				<div>{post.title}</div>
				<div>{post.body}</div>
				<div class="flex gap-1 flex-wrap">
					{#each post.tags as tag}
						<span class="badge variant-filled-secondary">{tag}</span>
					{/each}
				</div>
			</div>
		{/each}
	</div>
</div>

