import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.monitor.bus',
  appName: 'moniteurbus',
  webDir: 'dist/moniteurbus/browser',
  server: {
    androidScheme: 'https'
  }
};

export default config;
