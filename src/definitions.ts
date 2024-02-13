export interface MyCustomPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
