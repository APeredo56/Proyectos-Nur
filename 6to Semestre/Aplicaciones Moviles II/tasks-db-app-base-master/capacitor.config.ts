import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'io.ionic.starter',
  appName: 'tasks-db-app-base',
  webDir: 'www',
  server: {
    androidScheme: 'https'
  }
};

export default config;
