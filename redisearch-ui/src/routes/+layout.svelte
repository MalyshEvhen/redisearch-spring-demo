<script lang="ts">
	import Navigation from "$lib/components/Navigation.svelte";
	import "../app.postcss";
	import { AppShell, AppBar, Drawer, initializeStores, getDrawerStore, Toast } from "@skeletonlabs/skeleton";

	initializeStores();
	const drawerStore = getDrawerStore();

	function drawerOpen(): void {
		drawerStore.open();
	}
</script>

<Toast position="b" />

<Drawer>
	<Navigation />
</Drawer>

<!-- App Shell -->
<AppShell slotSidebarLeft="w-0 md:w-52 bg-surface-500/10">
	<svelte:fragment slot="header">
		<!-- App Bar -->
		<AppBar
			gridColumns="grid-cols-3"
			slotDefault="place-self-center"
			slotTrail="place-content-end"
		>
			<svelte:fragment slot="lead">
				<button class="md:hidden btn btn-sm mr-4" on:click={drawerOpen}>
					<span>
						<svg viewBox="0 0 100 80" class="fill-token w-4 h-4">
							<rect width="100" height="20"/>
							<rect y="30" width="100" height="20"/>
							<rect y="60" width="100" height="20"/>
						</svg>
					</span>
				</button>
			</svelte:fragment>
			<strong class="text-xl uppercase">Mind Blowing Museum</strong>
		</AppBar>
	</svelte:fragment>
	<svelte:fragment slot="sidebarLeft">
		<Navigation />
	</svelte:fragment>
	<div class="container px-10 mx-auto">
		<slot />
	</div>
</AppShell>
